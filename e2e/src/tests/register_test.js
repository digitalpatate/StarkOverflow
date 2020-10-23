Feature('Test register');

Scenario("Je me register corrctement",(I,registerPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-register@test.com','showman-register','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')
    I.amOnPage('/login')
});