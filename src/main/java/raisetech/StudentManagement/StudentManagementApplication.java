package raisetech.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "Enami Kouji";
	private String age ="37";

//    課題5用（あえて初期化しない）
    private Map<String, String> student;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/studentInfo")
	public String getStudentInfo() {
		return name + " " + age + "歳";
	}

	@PostMapping("/studentInfo")
	public void setStudentInfo(String name, String age) {
		this.name = name;
		this.age = age;
	}

	@PostMapping("/studentName")
	public void updateStudentName(String name) {
		this.name = name;
	}

//    課題5用
    @GetMapping("/student")
    public String getStudent() {
        List<String> member = new ArrayList<>();
        String returnString = "";
        if (student == null || student.isEmpty()) {
            return "データが存在しません";
        } else {
            for (String key : student.keySet()) {
                String temp = key + ":" + student.get(key) + "歳";
                member.add(temp);
            }
        }
        returnString = String.join("\n", member);
        return returnString;
    }

    @PostMapping("/student")
    public void setStudent(String name, String age) {
        // 登録をする際に、初期化しないとエラーになる
        if (student == null) {
            student = new HashMap<>();
        }
        student.put(name, age);
    }

    @PostMapping("/studentUpdate")
    public void updateStudentName(String originalName, String newName) {
        // 更新をする際に、初期化しないとエラーになる
        if (student == null) {
            student = new HashMap<>();
        } else {
            if(student.containsKey (originalName)){
                Map<String, String> temp = new HashMap<>();
                for(String key : student.keySet()) {
                    if(key.equals(originalName)) {
                        temp.put(newName, student.get(key));
                    } else {
                        temp.put(key, student.get(key));
                    }
                }
                student.clear();
                student = temp;
            }
        }
    }

}
