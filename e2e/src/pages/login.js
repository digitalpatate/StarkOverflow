const {I} = inject();

module.exports = {

    fields: {
        email: 'input[type=email]',
        password: 'input[type=password]'
    },
    submitButton: 'input[type=submit]',

    sendForm(email, password) {
        I.fillField(this.fields.email, email);
        I.fillField(this.fields.password, password);
        I.click(this.submitButton);
    },

}
