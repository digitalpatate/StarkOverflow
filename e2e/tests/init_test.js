Feature('My First Test');

Scenario('test login GitHub', (I) => {
  I.amOnPage('https://github.com');
  I.click(
    locate('a')
      .withAttr({ href: '/login' })
  );
  I.see('Sign in to GitHub');
  I.fillField('input[type=text]', 'test@test.test');
  I.fillField('input[type=password]', '1234');
  I.click('Sign in');
});

Scenario('login', (I, loginPage) => {
  I.amOnPage('https://github.com/login');
  loginPage.sendForm('john@doe.com','123456');
});
