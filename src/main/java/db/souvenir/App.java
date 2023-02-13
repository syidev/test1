package db.souvenir;

import db.souvenir.fixtures.Fixtures;
import db.souvenir.service.menu.MainMenuService;
import db.souvenir.ui.cli.MainCli;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        try {
            Fixtures.run();

            (new MainMenuService(new MainCli())).display();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
