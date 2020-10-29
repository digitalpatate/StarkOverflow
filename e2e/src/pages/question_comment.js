const {I} = inject();

module.exports = {

    fields: {
        content: 'textarea#commentContent-question',
    },
    submitButton: 'input#submitComment',

    sendForm(content) {
        I.fillField(this.fields.content, content);
        I.click(this.submitButton);
    },

}
