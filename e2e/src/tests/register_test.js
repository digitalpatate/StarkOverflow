Feature('Test register');

Scenario("Je me register",(I,registerPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test@test.com','showman','https://contacts.heig-vd.ch/picture/87','test firstname','test lastname','1234')
    I.amOnPage('/login');
});