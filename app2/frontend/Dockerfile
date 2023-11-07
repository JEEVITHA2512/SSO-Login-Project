FROM node:lts AS development

WORKDIR /app2/frontend
COPY package.json /app2/frontend/package.json
COPY package-lock.json /app2/frontend/package-lock.json

RUN npm ci
COPY ./public /app2/frontend/public
COPY ./src /app2/frontend/src


ENV CI=true
ENV PORT=8003

CMD [ "npm", "start" ]