let list = document.querySelectorAll(".navbar li");

function activeLink() {
    list.forEach((item) => {
        item.classList.remove("hovered");
    });
    this.classList.add("hovered");
}

list.forEach((item) => item.addEventListener("mouseover", activeLink));

// Menu Toggle
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navbar");
let main = document.querySelector("main");

toggle.onclick = function () {
    navigation.classList.toggle("active");
    main.classList.toggle("active");
};

document.addEventListener("DOMContentLoaded", function () {
  const accordions = document.querySelectorAll('.accordion');

  accordions.forEach(accordion => {
      let isOpen = false; // Переменная для отслеживания состояния аккордеона

      accordion.addEventListener('click', function () {
          isOpen = !isOpen; // Изменяем состояние на противоположное при каждом клике
          this.classList.toggle('active');
      });

      // Добавляем обработчик события для полей ввода внутри аккордеона
      const inputs = accordion.querySelectorAll('input');
      inputs.forEach(input => {
          input.addEventListener('click', function (event) {
              event.stopPropagation(); // Предотвращаем всплытие события, чтобы оно не достигло аккордеона и не вызвало его закрытие
          });
      });
  });
});


