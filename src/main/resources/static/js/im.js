new Vue({
    el: '#app',
    data() {
        return {visible: false, im_list: [], text_content: ''}
    },
    methods: {
        send_im_msg() {
            this.append_im_msg();
        },
        append_im_msg() {
            let data = this.text_content;
            this.im_list.push({
                'content': data,
            });
        },
        clear(){
            this.im_list = [];
        }
    },
})