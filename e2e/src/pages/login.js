const {I} = inject();

module.exports = {

    fields: {
        email: 'input#email[name=email]',
        password: 'input#password[type=password]'
    },
    submitButton: 'input[type=submit,value=Login]',

    sendForm(email, password) {
        I.fillField(this.fields.email, email);
        I.fillField(this.fields.password, password);
        I.click('Login');
    },

}
