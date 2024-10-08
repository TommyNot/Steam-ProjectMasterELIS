document.querySelector('.next').addEventListener('click', function () {
    let slider = document.querySelector('.slider');
    slider.appendChild(slider.firstElementChild);
});

document.querySelector('.prev').addEventListener('click', function () {
    let slider = document.querySelector('.slider');
    slider.insertBefore(slider.lastElementChild, slider.firstElementChild);
});
