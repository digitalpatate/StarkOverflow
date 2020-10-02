Feature('Login test');

Scenario('login', (I, loginPage) => {
    I.amOnPage('/login');
    loginPage.sendForm('john@doe.com','123456');
});