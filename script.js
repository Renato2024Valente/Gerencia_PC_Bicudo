let reservations = [];

function makeReservation() {
    const date = document.getElementById('date').value;
    const time = document.getElementById('time').value;

    if (date && time) {
        reservations.push({ date, time });
        updateReservationList();
    } else {
        alert('Por favor, preencha todos os campos');
    }
}

function resetReservations() {
    const password = prompt('Digite a senha para resetar as reservas:');
    if (password === 'senha123') {
        reservations = [];
        updateReservationList();
    } else {
        alert('Senha incorreta');
    }
}

function editReservation() {
    const password = prompt('Digite a senha para editar as reservas:');
    if (password === 'senha123') {
        const date = prompt('Digite a nova data (aaaa-mm-dd):');
        const time = prompt('Digite a nova hora (hh:mm):');
        if (date && time) {
            reservations = [{ date, time }];
            updateReservationList();
        } else {
            alert('Por favor, preencha todos os campos');
        }
    } else {
        alert('Senha incorreta');
    }
}

function printReservations() {
    let reservationText = 'Lista de Reservas:\n';
    reservations.forEach(reservation => {
        reservationText += `Data: ${reservation.date}, Hora: ${reservation.time}\n`;
    });
    alert(reservationText);
}

function updateReservationList() {
    const reservationList = document.getElementById('reservations');
    reservationList.innerHTML = '';
    reservations.forEach(reservation => {
        const listItem = document.createElement('li');
        listItem.textContent = `Data: ${reservation.date}, Hora: ${reservation.time}`;
        reservationList.appendChild(listItem);
    });
}
