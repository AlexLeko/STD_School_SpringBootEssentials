package br.com.devdojo;

import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void createShouldPersistData(){
        Student student = new Student("Alex", "alex@lk.com");

        this.studentRepository.save(student);

        assertThat(student.getId()).isNotNull();
        assertThat(student.getName()).isEqualTo("Alex");
        assertThat(student.getEmail()).isEqualTo("alex@lk.com");

    }

    @Test
    public void deleteShouldRemoveData(){
        Student student = new Student("Alex", "alex@lk.com");

        this.studentRepository.save(student);
        this.studentRepository.delete(student);

        Student std = studentRepository.findById(student.getId()).orElse(null);
        assertThat(std).isNull();
    }

    @Test
    public void updateShouldChangeAndPersistData(){
        Student student = new Student("Alex", "alex@lk.com");
        this.studentRepository.save(student);

        student.setName("Alex_22222");
        student.setEmail("alex_22222@teste.com");
        student = this.studentRepository.save(student);

        student = studentRepository.findById(student.getId()).get();
        assertThat(student.getName()).isEqualTo("Alex_22222");
        assertThat(student.getEmail()).isEqualTo("alex_22222@teste.com");
    }

    @Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase(){
        Student student = new Student("Alex", "alex@lk.com");
        Student student2 = new Student("alex", "alex_222@lk.com");

        this.studentRepository.save(student);
        this.studentRepository.save(student2);

        List<Student> studentList = studentRepository.findByNameIgnoreCaseContaining("alex");
        assertThat(studentList.size()).isEqualTo(2);
    }

    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo NOME é obrigatório");

        this.studentRepository.save(new Student());
    }

    @Test
    public void createWhenEmailIsNullShouldThrowConstraintViolationException(){
        thrown.expect(ConstraintViolationException.class);

        Student student = new Student();
        student.setName("Teste");

        this.studentRepository.save(student);
    }

    @Test
    public void createWhenEmailIsNotValidShouldThrowConstraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("E-mail Inválido");

        Student student = new Student();
        student.setName("Alex");
        student.setEmail("alex");

        this.studentRepository.save(student);
    }


}


