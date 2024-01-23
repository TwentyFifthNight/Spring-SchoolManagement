package com.school.schoolManagement.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.school.schoolManagement.Dto.ClassroomDto;
import com.school.schoolManagement.Dto.Converter.ClassroomDtoConverter;
import com.school.schoolManagement.Dto.Request.Classroom.CreateClassroomRequest;
import com.school.schoolManagement.Dto.Request.Classroom.UpdateClassroomRequest;
import com.school.schoolManagement.Exception.Classroom.ClassroomAlreadyExistException;
import com.school.schoolManagement.Exception.Classroom.ClassroomNotFoundException;
import com.school.schoolManagement.Exception.Classroom.ClassroomStudentListIsEmptyException;
import com.school.schoolManagement.Helper.BusinessMessage;
import com.school.schoolManagement.Model.Classroom;
import com.school.schoolManagement.Model.Student;
import com.school.schoolManagement.Repository.ClassroomRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
@Slf4j
public class ClassroomServiceImpl implements ClassroomService{
    private final ClassroomRepository classroomRepository;
    private final TeacherService teacherService;
    private final ClassroomDtoConverter converter;
    private final ResourceBundle LogMessage;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository,
                            TeacherService teacherService,
                            ClassroomDtoConverter converter) {
        this.classroomRepository = classroomRepository;
        this.teacherService = teacherService;
        this.converter = converter;

        LogMessage = ResourceBundle.getBundle("i18n/ClassroomLogMessage", Locale.getDefault());
    }

    public void createClassroom(CreateClassroomRequest request) throws ClassroomAlreadyExistException{
        checkIfClassroomExists(request.getSymbol());

        Classroom classroom = new Classroom();
        classroom.setSymbol(request.getSymbol());
        classroom.setSupervisor(teacherService.findTeacherByTeacherId(request.getSupervisorId()));

        classroomRepository.save(classroom);
        log.info(LogMessage.getString("Created"));
    }

    public void updateClassroom(Long id, UpdateClassroomRequest request) throws ClassroomNotFoundException{
        Classroom classroom = findClassroomByClassroomId(id);

        classroom.setSymbol(request.getSymbol());
        classroom.setSupervisor(teacherService.findTeacherByTeacherId(request.getSupervisorId()));

        classroomRepository.save(classroom);
        log.info(MessageFormat.format(LogMessage.getString("Updated"), id));
    }

    public void deleteClassroom(Long id) throws ClassroomNotFoundException{
        Classroom classroom = findClassroomByClassroomId(id);

        classroomRepository.delete(classroom);
        log.info(MessageFormat.format(LogMessage.getString("Deleted"), id));
    }

    public ClassroomDto findClassroomById(Long id) throws ClassroomNotFoundException{
        Classroom classroom = findClassroomByClassroomId(id);

        log.info(MessageFormat.format(LogMessage.getString("Found"), id));
        return converter.convert(classroom);
    }

    public List<ClassroomDto> findAllClassrooms() throws ClassroomNotFoundException{
        List<Classroom> classroomList = classroomRepository.findAll();

        if (classroomList.isEmpty()) {
            log.info(LogMessage.getString("ListEmpty"));
            throw new ClassroomNotFoundException(BusinessMessage.Classroom.LIST_EMPTY);
        }

        log.info(LogMessage.getString("Listed"));
        return converter.convert(classroomList);
    }

    public Classroom findClassroomByClassroomId(Long id) throws ClassroomNotFoundException{
        return classroomRepository.findById(id).orElseThrow(() -> {
            log.info(MessageFormat.format(LogMessage.getString("NotFound"), id));
            return new ClassroomNotFoundException(BusinessMessage.Classroom.NOT_FOUND);
        });
    }

    private void checkIfClassroomExists(String symbol) throws ClassroomAlreadyExistException {
        if (classroomRepository.existsBySymbol(symbol)) {
            log.error(MessageFormat.format(LogMessage.getString("AlreadyExists"), symbol));
            throw new ClassroomAlreadyExistException(BusinessMessage.Classroom.ALREADY_EXISTS);
        }
    }

    public void generateStudentListPDF(HttpServletResponse response, Long id) throws IOException, ClassroomStudentListIsEmptyException {
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
        } catch (IOException e) {
            throw new IOException("Error occurred while generating student list PDF");
        }

        Classroom classroom = findClassroomByClassroomId(id);
        if(classroom.getStudentList() == null || classroom.getStudentList().isEmpty()) {
            log.error(MessageFormat.format(LogMessage.getString("StudentListIsEmpty"), id));
            throw new ClassroomStudentListIsEmptyException(BusinessMessage.Classroom.STUDENT_LIST_EMPTY);
        }

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Student List", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingAfter(10);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{10, 50, 50});
        table.setWidthPercentage(60);
        table.addCell(new PdfPCell());
        table.addCell(new PdfPCell(new Paragraph("Students:")));
        table.addCell(new PdfPCell(new Paragraph("...................................")));

        List<Student> studentList = classroom.getStudentList();
        for(int i = 0; i < studentList.size(); i++){
            Student student = studentList.get(i);
            table.addCell(new PdfPCell(new Paragraph(String.valueOf(i + 1))));
            table.addCell(new PdfPCell(new Paragraph(student.getFirstName() + " " + student.getLastName())));
            table.addCell(new PdfPCell(new Paragraph("...................................")));
        }

        document.add(paragraph);
        document.add(table);
        document.close();
    }
}
