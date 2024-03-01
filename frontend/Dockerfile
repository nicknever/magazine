# Используем официальный образ Node.js для сборки фронтенда
FROM node:14 as build

# Устанавливаем зависимости и копируем файлы проекта
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Используем образ Nginx для запуска фронтенда
FROM nginx:alpine

# Копируем собранные файлы из предыдущего этапа в образ Nginx
COPY --from=build /app/public /usr/share/nginx/html

# Открываем порт 80 для веб-трафика
EXPOSE 80
