package business;

import model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectManager<T extends Subject> {
    private final List<T> subjects = new ArrayList<>();

    public void addSubject(T subject) throws InvalidCreditException {
        validateCredits(subject.getCredits());
        subjects.add(subject);
    }

    public boolean removeSubject(String code) {
        T subject = findByCode(code);

        if (subject == null) {
            return false;
        }

        subjects.remove(subject);
        return true;
    }

    public List<T> displaySubjects() {
        return subjects;
    }

    public List<T> searchByName(String name) {
        List<T> result = new ArrayList<>();

        for (T subject : subjects) {
            if (subject.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(subject);
            }
        }

        return result;
    }

    public List<T> filterByCredits() {
        List<T> result = new ArrayList<>();

        for (T subject : subjects) {
            if (subject.getCredits() > 3) {
                result.add(subject);
            }
        }

        return result;
    }

    public T findByCode(String code) {
        for (T subject : subjects) {
            if (subject.getCode().equalsIgnoreCase(code)) {
                return subject;
            }
        }

        return null;
    }

    public void validateCredits(int credits) throws InvalidCreditException {
        if (credits < 0 || credits > 10) {
            throw new InvalidCreditException("Số tín chỉ không hợp lệ!");
        }
    }
}
