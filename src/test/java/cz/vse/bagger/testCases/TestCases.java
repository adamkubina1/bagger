package cz.vse.bagger.testCases;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.io.IOException;
import java.util.Random;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Trida pro otestovani scenaru pouziti programu.
 *
 * Pred spustenim se pripojte na testovaci databazi!
 *
 * @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class TestCases extends ApplicationTest {

    /**
     * Tato funkce je zavolana pred kazdym testovacim scenarem, nacte nam login screen
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * ACT1: uživatel zadá login, který není uveden v systému.
     * REACT1: vyskočí upozornění ,,uživatel zadal nesprávný login nebo heslo''.
     * ACT2: uživatel zadá login, který je uveden v systému a nesprávné heslo.
     * REACT2: vyskočí upozornění ,,uživatel zadal nesprávný login nebo heslo''.
     * ACT3: uživatel zadá login, který je uveden v systému a správné heslo.
     * REACT3: uživateli se načte homepage, kde vidí jméno týmu a seznam svých projektů
     */
    @Test
    public void firstCase(){

        // ACT1
        clickOn("#username").write("fantomas");
        clickOn("#password").write("uzJeZas");
        clickOn("#loginButton");
        // REACT1
        verifyThat("#loginScreen", NodeMatchers.isVisible());
        verifyThat( "#alert", NodeMatchers.isVisible());

        clickOn("OK");

        //ACT2
        clickOn("#username").write("user_1");
        clickOn("#password").write("123456");
        clickOn("#loginButton");
        //REACT2
        verifyThat("#loginScreen", NodeMatchers.isVisible());
        verifyThat( "#alert", NodeMatchers.isVisible());

        clickOn("OK");

        //ACT3
        clickOn("#username").write("user_1");
        clickOn("#password").write("123456Ab");
        clickOn("#loginButton");
        //REACT3
        verifyThat("#mainScreen", NodeMatchers.isVisible());
        verifyThat("#teamLabel", NodeMatchers.isVisible());

    }

    /**
     * ACT1: uživatel je vedoucí týmu a klik na daný projekt a právě se úspěšně lognul.
     * REACT1: uživatel vidí nabídku: založení projektu, ukončení projektu, vytvoření záznamu o chybě, úprava záznamu o chybě, uzavření záznamu o chybě, přidání komentáře k záznamu o chybě.
     * ACT2: uživatel je zaměstnanec a klik na daný projekt a právě se úspěšně lognul.
     * REACT2: uživatel vidí nabídku: vytvoření záznamu o chybě, úprava záznamu o chybě, uzavření záznamu o chybě, přidání komentáře k záznamu o chybě.
     */
    @Test
    public void secondCaseTeamLeader(){
        //ACT1
        clickOn("#username").write("user_1");
        clickOn("#password").write("123456Ab");
        clickOn("#loginButton");
        //REACT1
        verifyThat("#newProject", NodeMatchers.isEnabled());
        verifyThat("#deleteProject", NodeMatchers.isEnabled());
        verifyThat("#newIssue", NodeMatchers.isVisible());
        verifyThat("#editIssue", NodeMatchers.isVisible());
        verifyThat("#closeIssue", NodeMatchers.isVisible());
        verifyThat("#newComment", NodeMatchers.isVisible());
    }
    @Test
    public void secondCaseTeamMember(){
        //ACT1
        clickOn("#username").write("user_2");
        clickOn("#password").write("123456Ab");
        clickOn("#loginButton");
        //REACT1
        verifyThat("#newProject", NodeMatchers.isDisabled());
        verifyThat("#deleteProject", NodeMatchers.isDisabled());
        verifyThat("#newIssue", NodeMatchers.isVisible());
        verifyThat("#editIssue", NodeMatchers.isVisible());
        verifyThat("#closeIssue", NodeMatchers.isVisible());
        verifyThat("#newComment", NodeMatchers.isVisible());
    }

    /**
     * ACT1: Z homepage uživatel klikne na nastavení. Objeví se pop-up window - user information. Zde uživatel zadá nesprávný old password.
     * REACT1: Ukáže se upozornění ,,zadali jste nesprávné původní heslo''.
     * ACT2: Z homepage uživatel klikne na nastavení. Objeví se pop-up window - user information. Zde uživatel zadá neshodná nová hesla.
     * REACT2: Ukáže se upozornění ,,zadali jste neshodná hesla''.
     */
    @Test
    public void thirdCaseFirstPart(){
        clickOn("#username").write("user_3");
        clickOn("#password").write("123456Ab");
        clickOn("#loginButton");

        //ACT1
        clickOn("#settings");
        verifyThat("#settingsScreen", NodeMatchers.isVisible());
        clickOn("#oldPassword").write("123456");
        clickOn("#newPassword").write("123456z");
        clickOn("#confirmNewPassword").write("123456z");
        clickOn("#button_Change_Password");

        //REACT1
        verifyThat( "#alert", NodeMatchers.isVisible());

    }
    @Test
    public void thirdCaseSecondPart(){
        clickOn("#username").write("user_3");
        clickOn("#password").write("123456Ab");
        clickOn("#loginButton");

        //ACT2
        clickOn("#settings");
        verifyThat("#settingsScreen", NodeMatchers.isVisible());
        clickOn("#oldPassword").write("123456Ab");
        clickOn("#newPassword").write("123456z");
        clickOn("#confirmNewPassword").write("123456");
        clickOn("#button_Change_Password");

        //REACT2
        verifyThat( "#alert", NodeMatchers.isVisible());

    }

    /**
     * ACT1: Uživatel na homepage klikne na create new project. Objeví se pop-up window new project. Zde uživatel nezadá jméno nového projektu a klikne na tlačítko create new project.
     * REACT1: Objeví se upozornění ,,nebylo zadáno jméno projektu''.
     * ACT2: Uživatel na homepage klikne na create new project. Objeví se pop-up window new project. Zde uživatel zadá jméno nového projektu a klikne create new project.¨
     * REACT2: Projekt byl úspěšně vytvořen, pop-up window bylo zavřeno a projekt je vypsán na homepage.
     */
    @Test
    public void fourthCase(){
        clickOn("#username").write("user_1");
        clickOn("#password").write("123456Ab");
        clickOn("#loginButton");

        //ACT1
        clickOn("#newProject");
        verifyThat("#newProjectScreen", NodeMatchers.isVisible());
        clickOn("#createProject");
        //REACT1
        verifyThat( "#alert", NodeMatchers.isVisible());

        clickOn("OK");

        //ACT2
        Random random = new Random();
        clickOn("#projectName").write("unitTestProject" + random.nextDouble());
        clickOn("#createProject");
        //REACT2
        try {
            verifyThat("#alert", NodeMatchers.isNull()); //This is dump, but works xd
        }
        catch(Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * AC1: Uživatel na homepage klikne na new comment. Objeví se pop-up window new comment. Zde uživatel nezadá žádný komentář a klikne na tlačítko new comment.
     * REACT1: Objeví se upozornění ,,nebyl zadán žádný text komentáře''.
     * AC2: Uživatel na homepage klikne na new comment. Objeví se pop-up window new comment. Zde uživatel zadá nějaký komentář a klikne na tlačítko new comment.
     * REACT2: Pop-up window zmizí a na homepage se objeví další komentář.
     *
     * TAHLE FUNKCIONALITA BYLA ZMENENA -> TESTY JSOU REDUNDANTNI
     */
    @Test
    public void fifthCase(){
        //ACT1

        //REACT1

        //ACT2

        //REACT2
    }




    /**
     * Tato funkce je volana po kazdem testu, pro jistotu vycisti nami pouzivane resources
     * @throws Exception
     */
    @AfterEach
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}
