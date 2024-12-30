import model.UserModel;
import view.UserView;
import controller.UserController;

public class Main {
    public static void main(String[] args) {
        UserModel model = new UserModel();
        UserView view = new UserView();
        new UserController(model, view);

        view.setVisible(true);
    }
}
