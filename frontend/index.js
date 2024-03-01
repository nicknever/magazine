const express = require('express');
const path = require('path');
const axios = require('axios');
const app = express();
app.use(express.json());

// Указываем папку со статическими файлами (HTML, CSS, JS)
app.use(express.static(path.join(__dirname, 'public')));

// Отправка статического HTML файла
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});

// Получение списка студентов
app.get('/student', (req, res) => {
  axios.get('http://localhost:8080/student')
    .then(response => {
      res.json(response.data);
    })
    .catch(error => {
      console.error('Ошибка получения данных:', error);
      res.status(500).send('Ошибка получения данных');
    });
});

// Создание нового студента
app.post('/student', (req, res) => {
  const newStudent = req.body;
  axios.post('http://localhost:8080/student', newStudent)
    .then(response => {
      res.json(response.data);
    })
    .catch(error => {
      console.error('Ошибка создания студента:', error);
      res.status(500).send('Ошибка создания студента');
    });
});

app.listen(3000, () => {
  console.log('Сервер запущен на порту 3000');
});
