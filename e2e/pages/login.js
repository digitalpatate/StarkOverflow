const { I } = inject();

module.exports = {

  // insert your locators and methods here
  fields: {
    email: 'input[type=text]',
    password: 'input[type=password]'
  },
  submitButton: 'input[type=submit]',

  sendForm(email, password) {
    I.fillField(this.fields.email, email);
    I.fillField(this.fields.password, password);
    I.click(this.submitButton);
  },

}
