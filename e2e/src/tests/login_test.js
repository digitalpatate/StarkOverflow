Feature('Login test');

Scenario("Je me login avec des crédential valide",async (I,registerPage,loginPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-login@test.com','showman-login','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')
    //await new Promise(r => setTimeout(r, 5000));

    I.amOnPage('/login')

    await loginPage.sendForm("test-login@test.com","1234")
    I.amOnPage('/profile')

});

Scenario("Je me login avec email non valide",async (I,registerPage,loginPage) => {

    I.amOnPage('/login')
    await loginPage.sendForm("test-login-no-exist@test.com","1234")

    I.seeElement(
        locate('div')
            .withAttr({ id: 'notification'})
            .withText('Cannot find user with this email')
    );

});

Scenario("Je me login avec un password non valide",async (I,registerPage,loginPage) => {
    I.amOnPage("/register")
    registerPage.sendForm('test-login-no-mdp@test.com','showman-login-no-mdp','https://contacts.heig-vd.ch/picture/87.jpg','test firstname','test lastname','1234')
    //await new Promise(r => setTimeout(r, 5000));

    I.amOnPage('/login')
    await loginPage.sendForm("test-login-no-mdp@test.com","no-valide")
    //await new Promise(r => setTimeout(r, 5000));

    I.seeElement(
        locate('div')
            .withAttr({ id: 'notification'})
            .withText('Verification of credentials failed!')
    );
});