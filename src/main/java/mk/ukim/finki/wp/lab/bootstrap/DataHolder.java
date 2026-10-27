package mk.ukim.finki.wp.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;
    @PostConstruct
    public void init(){
        workshops = new ArrayList<>();
        workshops.add(new Workshop("Java Basics", "Programming", 1500, 20));
        workshops.add(new Workshop("Spring Boot", "Programming", 2500, 15));
        workshops.add(new Workshop("Web Design", "Design", 1800, 10));
        workshops.add(new Workshop("UI/UX Fundamentals", "Design", 2000, 12));
        workshops.add(new Workshop("Digital Marketing", "Marketing", 1200, 25));
        workshops.add(new Workshop("SEO Masterclass", "Marketing", 1700, 8));
        workshops.add(new Workshop("Data Analysis", "Data", 2200, 14));
        workshops.add(new Workshop("Machine Learning", "Data", 3000, 5));
        workshops.add(new Workshop("Docker & DevOps", "DevOps", 2700, 0)); // нема места
        workshops.add(new Workshop("Cyber Security", "Security", 2900, 7));

        registrations = new ArrayList<>();

    }

}
