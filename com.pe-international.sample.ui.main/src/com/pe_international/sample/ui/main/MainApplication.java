/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.ui.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pe_international.sample.ui.main.service.ActionContribution;
import com.pe_international.sample.ui.main.service.ViewContribution;
import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class MainApplication extends Application {

   private static final long serialVersionUID = 1L;
   private final Logger logger = LoggerFactory.getLogger(MainApplication.class);

   private final List<ViewContribution> viewContributions = Collections
         .synchronizedList(new ArrayList<ViewContribution>());
   private final List<ActionContribution> actionContributions = Collections
         .synchronizedList(new ArrayList<ActionContribution>());
   private final Map<ActionContribution, Button> buttonActionMap = Collections
         .synchronizedMap(new HashMap<ActionContribution, Button>());
   private final Map<ActionContribution, MenuItem> menuActionMap = Collections
         .synchronizedMap(new HashMap<ActionContribution, MenuItem>());

   private Window main;
   private VerticalLayout mainLayout;
   private TabSheet tabSheet;
   private Window aboutWindow;

   private volatile boolean initialized = false;

   private HorizontalLayout toolbar;

   private MenuBar.MenuItem actionMenu;

   @Override
   public void init() {
      logger.info("Initializing PE International OSGi samples ...");
      setTheme(Reindeer.THEME_NAME);
      // setTheme(Runo.THEME_NAME);
      // setTheme("demo");
      main = new Window("PE International OSGi Samples");
      mainLayout = (VerticalLayout) main.getContent();
      mainLayout.setMargin(false);
      mainLayout.setStyleName("blue");
      setMainWindow(main);

      mainLayout.setSizeFull();
      mainLayout.addComponent(getMenu());

      HorizontalLayout header = new HorizontalLayout();

      header.addComponent(getHeader());
      header.addComponent(getToolbar());
      mainLayout.addComponent(header);

      CssLayout margin = new CssLayout();
      margin.setMargin(false, true, true, true);
      margin.setSizeFull();
      tabSheet = new TabSheet();
      tabSheet.setSizeFull();
      margin.addComponent(tabSheet);
      mainLayout.addComponent(margin);
      mainLayout.setExpandRatio(margin, 1);

      for (ViewContribution viewContribution : viewContributions) {
         tabSheet.addTab(viewContribution.getView(this), viewContribution.getName(),
               new ThemeResource(viewContribution.getIcon()));
      }

      for (ActionContribution actionContribution : actionContributions) {
         addActionContribution(actionContribution);
      }

      initialized = true;
   }

   @SuppressWarnings("serial")
   private MenuBar getMenu() {
      MenuBar menubar = new MenuBar();
      menubar.setWidth("100%");
      actionMenu = menubar.addItem("Action", null);

      actionMenu.addItem("Built-in Action...", new Command() {
         @Override
         public void menuSelected(MenuItem selectedItem) {
            main.showNotification("Built-in Action executed!");
         }
      });
      actionMenu.addSeparator();

      final MenuBar.MenuItem viewMenu = menubar.addItem("Help", null);
      viewMenu.addItem("About...", new Command() {
         @Override
         public void menuSelected(MenuItem selectedItem) {
            main.addWindow(getAboutDialog());
         }
      });

      return menubar;
   }

   private Layout getHeader() {
      HorizontalLayout header = new HorizontalLayout();
      header.setWidth("100%");
      header.setMargin(true);
      header.setSpacing(true);
      // header.setStyleName(Reindeer.LAYOUT_BLACK);

      CssLayout titleLayout = new CssLayout();
      H1 title = new H1("PE International OSGi Samples");
      titleLayout.addComponent(title);

      SmallText description = new SmallText("Select the tabs that deployed as OSGi bundles.");
      description.setSizeUndefined();
      titleLayout.addComponent(description);
      titleLayout.addComponent(description);

      header.addComponent(titleLayout);

      return header;
   }

   private Layout getToolbar() {
      if (toolbar == null) {
         toolbar = new HorizontalLayout();
         toolbar.setMargin(true);
         toolbar.setSpacing(true);
      }
      return toolbar;
   }

   private Window getAboutDialog() {
      if (aboutWindow == null) {
         aboutWindow = new Window("About...");
         aboutWindow.setModal(true);
         aboutWindow.setWidth("400px");

         VerticalLayout layout = (VerticalLayout) aboutWindow.getContent();
         layout.setMargin(true);
         layout.setSpacing(true);
         layout.setStyleName("blue");

         CssLayout titleLayout = new CssLayout();
         H2 title = new H2("Dynamic Vaadin OSGi Demo");
         titleLayout.addComponent(title);
         SmallText description = new SmallText("<br>Copyright (c) PE INTERNATIONAL and others.<br>"
               + "The icons are from the Silk icon set by Mark James<br>"
               + "<a href=\"http://www.famfamfam.com/lab/icons/silk/\">http://www.famfamfam.com/lab/icons/silk/</a>");
         description.setSizeUndefined();
         description.setContentMode(Label.CONTENT_XHTML);

         titleLayout.addComponent(description);
         aboutWindow.addComponent(titleLayout);

         @SuppressWarnings("serial")
         Button close = new Button("Close", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
               (aboutWindow.getParent()).removeWindow(aboutWindow);
            }

         });
         layout.addComponent(close);
         layout.setComponentAlignment(close, "right");
      }
      return aboutWindow;
   }

   public void bindViewContribution(ViewContribution viewContribution) {
      logger.debug("bindViewContribution()");
      viewContributions.add(viewContribution);
      if (initialized) {
         tabSheet.addTab(viewContribution.getView(this), viewContribution.getName(),
               new ThemeResource(viewContribution.getIcon()));
      }
   }

   public void unbindViewContribution(ViewContribution viewContribution) {
      logger.debug("unbindViewContribution()");
      viewContributions.remove(viewContribution);
      if (initialized) {
         tabSheet.removeComponent(viewContribution.getView(this));
      }
   }

   public void bindActionContribution(final ActionContribution actionContribution) {
      logger.debug("bindActionContribution()");
      if (initialized) {
         addActionContribution(actionContribution);
      }
      actionContributions.add(actionContribution);
   }

   private void addActionContribution(final ActionContribution actionContribution) {
      final Application application = this;
      Button button = new Button(actionContribution.getText());
      button.setIcon(new ThemeResource(actionContribution.getIcon()));
      button.addListener(new ClickListener() {
         private static final long serialVersionUID = 1L;

         @Override
         public void buttonClick(ClickEvent event) {
            actionContribution.execute(application);
         }
      });
      getToolbar().addComponent(button);
      buttonActionMap.put(actionContribution, button);

      @SuppressWarnings("serial")
      MenuItem menuItem = actionMenu.addItem(actionContribution.getText(), new Command() {
         @Override
         public void menuSelected(MenuItem selectedItem) {
            actionContribution.execute(application);
         }
      });
      menuItem.setIcon(new ThemeResource(actionContribution.getIcon()));
      menuActionMap.put(actionContribution, menuItem);
   }

   public void unbindActionContribution(ActionContribution actionContribution) {
      logger.debug("unbindActionContribution()");
      Button button = buttonActionMap.get(actionContribution);
      toolbar.removeComponent(button);
      buttonActionMap.remove(actionContribution);

      MenuItem menuItem = menuActionMap.get(actionContribution);
      actionMenu.removeChild(menuItem);
      menuActionMap.remove(actionContribution);

      actionContributions.remove(actionContribution);
   }

   @SuppressWarnings("serial")
   class H1 extends Label {
      public H1(String caption) {
         super(caption);
         setSizeUndefined();
         setStyleName(Reindeer.LABEL_H1);
      }
   }

   @SuppressWarnings("serial")
   class H2 extends Label {
      public H2(String caption) {
         super(caption);
         setSizeUndefined();
         setStyleName(Reindeer.LABEL_H2);
      }
   }

   @SuppressWarnings("serial")
   class SmallText extends Label {
      public SmallText(String caption) {
         super(caption);
         setStyleName(Reindeer.LABEL_SMALL);
      }
   }

}