/**
 * Created by zero on 15/6/24.
 */
var gulp = require('gulp'),
    gulpLoadPlugins = require('gulp-load-plugins'),
    shell = require('gulp-shell'),
    processhtml = require('gulp-processhtml')
    opts = {};

gulp.task('default', function () {
    // place code for your default task here
});

gulp.task('scripts', shell.task([
    'r.js -o build.js'
]));

gulp.task('processhtml', function () {
    return gulp.src('src/main/webapp/admin/*.jsp')
        .pipe(processhtml())
        .pipe(gulp.dest('src/main/webapp/admin-dest'));
});