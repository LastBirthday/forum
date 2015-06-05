function _ThemeUtil() {
    this.deleteTheme = function(id) {
        if (confirm("Are you sure?")) {
            window.location = "/deleteTheme/" + id;
        }
    }
}

var ThemeUtil = new _ThemeUtil();