var opt = -999999;
$('.optimista').each(function ()
{
    $this = parseInt($(this).text());
    if ($this > opt)
        opt = $this;
});
$(".optimista").each(function () {
    $cell = $(this);
    if (parseInt($cell.text(), 10) === opt) {
        $cell.css("background-color", "#61BF71");
        $cell.css("color", "white");
    }
});

var pes = -999999;
$('.pesimista').each(function ()
{
    $this = parseInt($(this).text());
    if ($this > pes)
        pes = $this;
});
$(".pesimista").each(function () {
    $cell = $(this);
    if (parseInt($cell.text(), 10) === pes) {
        $cell.css("background-color", "#61BF71");
        $cell.css("color", "white");
    }
});

var lap = -999999;
$('.laplace').each(function ()
{
    $this = parseInt($(this).text());
    if ($this > lap)
        lap = $this;
});
$(".laplace").each(function () {
    $cell = $(this);
    if (parseInt($cell.text(), 10) === lap) {
        $cell.css("background-color", "#61BF71");
        $cell.css("color", "white");
    }
});

var hur = -999999;
$('.hurwicz').each(function ()
{
    $this = parseInt($(this).text());
    if ($this > hur)
        hur = $this;
});
$(".hurwicz").each(function () {
    $cell = $(this);
    if (parseInt($cell.text(), 10) === hur) {
        $cell.css("background-color", "#61BF71");
        $cell.css("color", "white");
    }
});

var sav = 999999;
$('.savage').each(function ()
{
    $this = parseInt($(this).text());
    if ($this < sav)
        sav = $this;
});
$(".savage").each(function () {
    $cell = $(this);
    if (parseInt($cell.text(), 10) === sav) {
        $cell.css("background-color", "#61BF71");
        $cell.css("color", "white");
    }
});