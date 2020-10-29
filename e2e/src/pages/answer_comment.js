const {I} = inject();

module.exports = {

    fields: {
        content: 'textarea#commentContent-answer',
    },
    submitButton: 'input#submitCommentAnswer',

    sendForm(content) {
        I.fillField(this.fields.content, content);
        I.click(this.submitButton);
    },

}
