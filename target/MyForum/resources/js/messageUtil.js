function _MessageUtil() {
    this.deleteMessage = function(id) {
        if (confirm("Are you sure?")) {
            window.location = "/deleteMessage/" + id;
        }
    }
}

var MessageUtil = new _MessageUtil();
