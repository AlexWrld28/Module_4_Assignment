package com.example.week4ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Week4DemoApp extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Week 4 Demo UI Lab");
        stage.setWidth(1180);
        stage.setHeight(760);
        stage.setMinWidth(980);
        stage.setMinHeight(680);
        showSplash();
        stage.show();
    }

    private void showSplash() {
        stage.setScene(createScene(createSplashScreen(), "/styles/splash.css"));
    }

    private void showLogin() {
        stage.setScene(createScene(createLoginScreen(), "/styles/auth.css"));
    }

    private void showRegister() {
        stage.setScene(createScene(createRegisterScreen(), "/styles/auth.css"));
    }

    private void showLanding() {
        stage.setScene(createScene(createLandingScreen(), "/styles/landing.css"));
    }

    private Scene createScene(Parent root, String cssPath) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/common.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        return scene;
    }

    private Parent createSplashScreen() {
        StackPane root = new StackPane();
        root.getStyleClass().add("splash-root");

        VBox card = new VBox(18);
        card.getStyleClass().add("splash-card");
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(540);
        card.setPadding(new Insets(40));

        StackPane logoCircle = new StackPane();
        logoCircle.getStyleClass().add("logo-circle");
        Label logoText = new Label("A");
        logoText.getStyleClass().add("logo-letter");
        logoCircle.getChildren().add(logoText);

        Label title = new Label("Atlas Workspace");
        title.getStyleClass().add("splash-title");

        Label subtitle = new Label(
                "A polished classroom UI demo with a splash screen, login flow, registration form, and landing page.");
        subtitle.getStyleClass().add("splash-subtitle");
        subtitle.setWrapText(true);
        subtitle.setMaxWidth(430);

        Button startButton = new Button("Get Started");
        startButton.getStyleClass().addAll("primary-button", "large-button");
        startButton.setOnAction(e -> showLogin());

        Hyperlink skipLink = new Hyperlink("Preview landing screen");
        skipLink.getStyleClass().add("subtle-link");
        skipLink.setOnAction(e -> showLanding());

        card.getChildren().addAll(logoCircle, title, subtitle, startButton, skipLink);
        root.getChildren().add(card);
        return root;
    }

    private Parent createLoginScreen() {
        HBox root = new HBox();
        root.getStyleClass().add("auth-root");

        VBox brandingPane = buildAuthBrandingPane(
                "Welcome back",
                "Sign in to continue to your dashboard.",
                "Clean layout, modern spacing, and separate CSS files keep the project aligned with the lab's UI focus.");

        VBox formPane = new VBox(18);
        formPane.getStyleClass().add("form-pane");
        formPane.setAlignment(Pos.CENTER);
        HBox.setHgrow(formPane, Priority.ALWAYS);

        VBox formCard = new VBox(14);
        formCard.getStyleClass().add("form-card");
        formCard.setMaxWidth(410);

        Label heading = new Label("Login");
        heading.getStyleClass().add("form-title");

        Label body = new Label("Use any values you want. This screen is for UI presentation only.");
        body.getStyleClass().add("form-subtitle");
        body.setWrapText(true);

        TextField emailField = new TextField();
        emailField.setPromptText("Email address");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        HBox optionsRow = new HBox(10);
        optionsRow.setAlignment(Pos.CENTER_LEFT);
        CheckBox remember = new CheckBox("Remember me");
        Hyperlink forgot = new Hyperlink("Forgot password?");
        forgot.getStyleClass().add("inline-link");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        optionsRow.getChildren().addAll(remember, spacer, forgot);

        Button loginButton = new Button("Sign In");
        loginButton.getStyleClass().addAll("primary-button", "full-width");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setOnAction(e -> showLanding());

        Button backButton = new Button("Back to Splash");
        backButton.getStyleClass().addAll("secondary-button", "full-width");
        backButton.setMaxWidth(Double.MAX_VALUE);
        backButton.setOnAction(e -> showSplash());

        HBox footerRow = new HBox();
        footerRow.setAlignment(Pos.CENTER);
        Label noAccount = new Label("Don't have an account?");
        noAccount.getStyleClass().add("small-muted");
        Hyperlink registerLink = new Hyperlink("Create one");
        registerLink.getStyleClass().add("inline-link");
        registerLink.setOnAction(e -> showRegister());
        footerRow.getChildren().addAll(noAccount, new Label("  "), registerLink);

        formCard.getChildren().addAll(
                heading,
                body,
                new Label("Email"),
                emailField,
                new Label("Password"),
                passwordField,
                optionsRow,
                loginButton,
                backButton,
                footerRow
        );

        formPane.getChildren().add(formCard);
        root.getChildren().addAll(brandingPane, formPane);
        return root;
    }

    private Parent createRegisterScreen() {
        HBox root = new HBox();
        root.getStyleClass().add("auth-root");

        VBox brandingPane = buildAuthBrandingPane(
                "Create your account",
                "Use this registration screen if your instructor expects it in the flow.",
                "The buttons only navigate between screens so the design stays front and center.");

        VBox formPane = new VBox(18);
        formPane.getStyleClass().add("form-pane");
        formPane.setAlignment(Pos.CENTER);
        HBox.setHgrow(formPane, Priority.ALWAYS);

        VBox formCard = new VBox(14);
        formCard.getStyleClass().add("form-card");
        formCard.setMaxWidth(430);

        Label heading = new Label("Register");
        heading.getStyleClass().add("form-title");

        Label body = new Label("A presentation-ready sign-up form with polished spacing and hierarchy.");
        body.getStyleClass().add("form-subtitle");
        body.setWrapText(true);

        TextField nameField = new TextField();
        nameField.setPromptText("Full name");

        TextField emailField = new TextField();
        emailField.setPromptText("School email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Create password");

        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Confirm password");

        CheckBox terms = new CheckBox("I agree to the terms and classroom demo policy");

        Button createAccount = new Button("Create Account");
        createAccount.getStyleClass().addAll("primary-button", "full-width");
        createAccount.setMaxWidth(Double.MAX_VALUE);
        createAccount.setOnAction(e -> showLanding());

        Button backToLogin = new Button("Back to Login");
        backToLogin.getStyleClass().addAll("secondary-button", "full-width");
        backToLogin.setMaxWidth(Double.MAX_VALUE);
        backToLogin.setOnAction(e -> showLogin());

        HBox footerRow = new HBox();
        footerRow.setAlignment(Pos.CENTER);
        Label haveAccount = new Label("Already registered?");
        haveAccount.getStyleClass().add("small-muted");
        Hyperlink signIn = new Hyperlink("Sign in");
        signIn.getStyleClass().add("inline-link");
        signIn.setOnAction(e -> showLogin());
        footerRow.getChildren().addAll(haveAccount, new Label("  "), signIn);

        formCard.getChildren().addAll(
                heading,
                body,
                new Label("Full name"),
                nameField,
                new Label("Email"),
                emailField,
                new Label("Password"),
                passwordField,
                new Label("Confirm password"),
                confirmField,
                terms,
                createAccount,
                backToLogin,
                footerRow
        );

        formPane.getChildren().add(formCard);
        root.getChildren().addAll(formPane, brandingPane);
        return root;
    }

    private VBox buildAuthBrandingPane(String titleText, String subtitleText, String detailText) {
        VBox brandingPane = new VBox(18);
        brandingPane.getStyleClass().add("branding-pane");
        brandingPane.setPadding(new Insets(42));
        brandingPane.setPrefWidth(480);
        brandingPane.setAlignment(Pos.TOP_LEFT);

        Label eyebrow = new Label("Week 4 UI Lab");
        eyebrow.getStyleClass().add("eyebrow-label");

        Label title = new Label(titleText);
        title.getStyleClass().add("branding-title");
        title.setWrapText(true);

        Label subtitle = new Label(subtitleText);
        subtitle.getStyleClass().add("branding-subtitle");
        subtitle.setWrapText(true);

        VBox featureCard = new VBox(12);
        featureCard.getStyleClass().add("feature-card");

        Label featureTitle = new Label("Design highlights");
        featureTitle.getStyleClass().add("feature-title");

        Label bulletOne = new Label("• Soft gradients and shadowed cards");
        Label bulletTwo = new Label("• Separate screen-specific CSS files");
        Label bulletThree = new Label("• UI-first flow with minimal logic");
        Label detail = new Label(detailText);
        detail.setWrapText(true);
        detail.getStyleClass().add("feature-detail");

        featureCard.getChildren().addAll(featureTitle, bulletOne, bulletTwo, bulletThree, new Separator(), detail);
        brandingPane.getChildren().addAll(eyebrow, title, subtitle, featureCard);
        return brandingPane;
    }

    private Parent createLandingScreen() {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("landing-root");

        VBox sidebar = new VBox(16);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPadding(new Insets(24));
        sidebar.setPrefWidth(240);

        Label appName = new Label("Atlas");
        appName.getStyleClass().add("sidebar-title");

        Button dashboardBtn = new Button("Dashboard");
        Button analyticsBtn = new Button("Analytics");
        Button projectsBtn = new Button("Projects");
        Button settingsBtn = new Button("Settings");
        Button logoutBtn = new Button("Log Out");

        dashboardBtn.getStyleClass().addAll("nav-button", "nav-button-active");
        analyticsBtn.getStyleClass().add("nav-button");
        projectsBtn.getStyleClass().add("nav-button");
        settingsBtn.getStyleClass().add("nav-button");
        logoutBtn.getStyleClass().addAll("nav-button", "logout-button");
        dashboardBtn.setMaxWidth(Double.MAX_VALUE);
        analyticsBtn.setMaxWidth(Double.MAX_VALUE);
        projectsBtn.setMaxWidth(Double.MAX_VALUE);
        settingsBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn.setOnAction(e -> showLogin());

        VBox navGroup = new VBox(10, dashboardBtn, analyticsBtn, projectsBtn, settingsBtn);
        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);
        sidebar.getChildren().addAll(appName, navGroup, sidebarSpacer, logoutBtn);

        VBox content = new VBox(20);
        content.getStyleClass().add("content-area");
        content.setPadding(new Insets(24));

        HBox topBar = new HBox(14);
        topBar.setAlignment(Pos.CENTER_LEFT);

        VBox titleBlock = new VBox(4);
        Label pageTitle = new Label("Landing Screen");
        pageTitle.getStyleClass().add("page-title");
        Label pageSubtitle = new Label("A dashboard-style landing page for your classroom submission.");
        pageSubtitle.getStyleClass().add("page-subtitle");
        titleBlock.getChildren().addAll(pageTitle, pageSubtitle);

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);

        TextField search = new TextField();
        search.setPromptText("Search");
        search.getStyleClass().add("search-field");
        search.setMaxWidth(220);

        Button profileButton = new Button("AZ");
        profileButton.getStyleClass().add("profile-chip");

        topBar.getChildren().addAll(titleBlock, topSpacer, search, profileButton);

        HBox heroSection = new HBox(18);
        VBox heroCard = new VBox(12);
        heroCard.getStyleClass().add("hero-card");
        HBox.setHgrow(heroCard, Priority.ALWAYS);

        Label heroTitle = new Label("Good morning, Alex");
        heroTitle.getStyleClass().add("hero-title");
        Label heroText = new Label("This landing page is intentionally visual-heavy so the UI design stands out during grading.");
        heroText.getStyleClass().add("hero-text");
        heroText.setWrapText(true);

        HBox heroActions = new HBox(10);
        Button openProjects = new Button("Open Projects");
        openProjects.getStyleClass().add("primary-button");
        Button inviteTeam = new Button("Invite Team");
        inviteTeam.getStyleClass().add("secondary-button");
        heroActions.getChildren().addAll(openProjects, inviteTeam);
        heroCard.getChildren().addAll(heroTitle, heroText, heroActions);

        VBox statusCard = new VBox(10);
        statusCard.getStyleClass().add("mini-card");
        statusCard.setPrefWidth(220);
        Label statusTitle = new Label("Today's Focus");
        statusTitle.getStyleClass().add("mini-card-title");
        Label statusText = new Label("UI Review\nCSS Cleanup\nGitHub Submission");
        statusText.getStyleClass().add("mini-card-value");
        statusCard.getChildren().addAll(statusTitle, statusText);
        heroSection.getChildren().addAll(heroCard, statusCard);

        HBox statsRow = new HBox(16,
                buildStatCard("Active Projects", "12", "+3 this week"),
                buildStatCard("Pending Tasks", "28", "8 due today"),
                buildStatCard("Team Members", "7", "2 online now"));

        HBox bottomRow = new HBox(18);
        VBox tasksCard = new VBox(12);
        tasksCard.getStyleClass().add("panel-card");
        HBox.setHgrow(tasksCard, Priority.ALWAYS);

        Label tasksTitle = new Label("Upcoming Tasks");
        tasksTitle.getStyleClass().add("panel-title");
        tasksCard.getChildren().addAll(
                tasksTitle,
                buildTaskRow("Finalize splash screen", "High"),
                buildTaskRow("Adjust login spacing", "Medium"),
                buildTaskRow("Polish landing page cards", "Medium"),
                buildTaskRow("Push repo to GitHub", "High")
        );

        VBox activityCard = new VBox(12);
        activityCard.getStyleClass().add("panel-card");
        activityCard.setPrefWidth(300);
        Label activityTitle = new Label("Recent Activity");
        activityTitle.getStyleClass().add("panel-title");
        activityCard.getChildren().addAll(
                activityTitle,
                buildActivityRow("09:00 AM", "Splash screen updated"),
                buildActivityRow("10:20 AM", "Login form restyled"),
                buildActivityRow("11:05 AM", "Registration screen added"),
                buildActivityRow("11:40 AM", "Landing screen finalized")
        );

        bottomRow.getChildren().addAll(tasksCard, activityCard);

        content.getChildren().addAll(topBar, heroSection, statsRow, bottomRow);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("content-scroll");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.setLeft(sidebar);
        root.setCenter(scrollPane);
        return root;
    }

    private VBox buildStatCard(String title, String value, String footer) {
        VBox card = new VBox(8);
        card.getStyleClass().add("stat-card");
        HBox.setHgrow(card, Priority.ALWAYS);
        card.setMaxWidth(Double.MAX_VALUE);

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("stat-title");
        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("stat-value");
        Label footerLabel = new Label(footer);
        footerLabel.getStyleClass().add("stat-footer");
        card.getChildren().addAll(titleLabel, valueLabel, footerLabel);
        return card;
    }

    private HBox buildTaskRow(String title, String priority) {
        HBox row = new HBox(12);
        row.getStyleClass().add("task-row");
        row.setAlignment(Pos.CENTER_LEFT);

        VBox textBlock = new VBox(4);
        HBox.setHgrow(textBlock, Priority.ALWAYS);
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("task-title");
        Label noteLabel = new Label("UI-only placeholder item");
        noteLabel.getStyleClass().add("task-note");
        textBlock.getChildren().addAll(titleLabel, noteLabel);

        Label priorityLabel = new Label(priority);
        priorityLabel.getStyleClass().add("pill-label");
        row.getChildren().addAll(textBlock, priorityLabel);
        return row;
    }

    private HBox buildActivityRow(String time, String text) {
        HBox row = new HBox(12);
        row.getStyleClass().add("activity-row");
        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("activity-time");
        Label textLabel = new Label(text);
        textLabel.getStyleClass().add("activity-text");
        textLabel.setWrapText(true);
        row.getChildren().addAll(timeLabel, textLabel);
        return row;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
