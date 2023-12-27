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
        accordion.addEventListener('click', function () {
            this.classList.toggle('active');
        });
    });
});


function initMap() {
    // Specify the initial location for the map
    let initialLocation = { lat: 37.7749, lng: -122.4194 }; // Replace with your desired coordinates

    // Create a map centered at the initial location
    let map = new google.maps.Map(document.getElementById('google-map'), {
        center: initialLocation,
        zoom: 10, // Adjust the zoom level as needed
    });

    // You can add markers or other map functionality as per your requirements
    // Example: Add a marker at the initial location
    let marker = new google.maps.Marker({
        position: initialLocation,
        map: map,
        title: 'Marker Title',
    });
}

// Initialize the map when the page loads
window.onload = function () {
    initMap();
};

