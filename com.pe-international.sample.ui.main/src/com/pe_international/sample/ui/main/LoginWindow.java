package com.pe_international.sample.ui.main;

import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class LoginWindow extends Window {

   private final ShortcutAction enterKey = new ShortcutAction("Login",
         ShortcutAction.KeyCode.ENTER, null);
   private Button btnLogin = new Button("Login");
   private TextField login = new TextField("Username");
   private PasswordField password = new PasswordField("Password");
   private MainApplication mainApplication;


   public LoginWindow(MainApplication mainApplication) {
      super("Authentication Required !");
      this.mainApplication = mainApplication;
      setName("login");
      initUI();
   }

   private void tryToLogIn() {
      try {
         mainApplication.authenticate((String) login.getValue(), (String) password.getValue());
         open(new ExternalResource(mainApplication.getURL()));
      } catch (Exception e) {
         showNotification("Login not possible.", Notification.TYPE_WARNING_MESSAGE);
      }

   }


   private void initUI() {
       center();
       
       VerticalLayout layout = new VerticalLayout();
       layout.setSpacing(true);
       layout.setMargin(true);
       setContent(layout);
       
       
       Panel p = new Panel();
      Link lnk = new Link(null, new ExternalResource(
            "http://www.pe-international.com"));
      lnk.setIcon(new ThemeResource("icons/PE_International.jpg"));
      login.setInputPrompt("login");
      password.setInputPrompt("password");

      p.addComponent(lnk);
      addComponent(p);
      
      addComponent(new Label("Please login in order to use the application"));
      addComponent(login);
      addComponent(password);
      addComponent(btnLogin);
      
//      layout.setComponentAlignment(lnk, Alignment.MIDDLE_CENTER);
//               layout.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
//               layout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
//               layout.setComponentAlignment(btnLogin, Alignment.MIDDLE_CENTER);
//               layout.setSpacing(true);
//               layout.setMargin(true);

      btnLogin.focus();
      btnLogin.addListener(new Button.ClickListener() {
         public void buttonClick(Button.ClickEvent event) {
            tryToLogIn();
         }
      });


      // Keyboard navigation - enter key is a shortcut to login
      addActionHandler(new Action.Handler() {
         public Action[] getActions(Object target, Object sender) {
            return new Action[] { enterKey };
         }

         public void handleAction(Action action, Object sender, Object target) {
            tryToLogIn();
         }

      });
   }


}
