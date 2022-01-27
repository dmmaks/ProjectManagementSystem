import databaseInteractor.DatabaseInteractor;
import databaseInteractor.PostgreInteractor;
import entities.*;

public class Main {
    public static void main(String[] args) {
        DatabaseInteractor interactor = null;
        try {
            interactor = new PostgreInteractor();
//            Company company = new Company(5, "nam", "2000-02-01");
//            boolean result = interactor.updateCompany(company);
            System.out.println(interactor.getDevelopersPerProject());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
