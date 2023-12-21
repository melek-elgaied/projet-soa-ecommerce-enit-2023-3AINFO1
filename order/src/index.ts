import fs from 'fs';
import dotenv from 'dotenv';
dotenv.config();
const envConfig = dotenv.parse(fs.readFileSync('.env'));
for (const k in envConfig) {
  process.env[k] = envConfig[k];
}
if (fs.existsSync('.env.local')) {
  const localEnvConfig = dotenv.parse(fs.readFileSync('.env.local'));
  for (const k in localEnvConfig) {
    process.env[k] = localEnvConfig[k];
  }
}

import express, { Express, Request, Response } from 'express';
import http, { Server } from 'http';
import connectDb from '@config/database';

import ratingRouter from '@route/rating'
import cors from 'cors';
import bodyParser from 'body-parser';
connectDb();

const app: Express = express();
const allowedOrigins = ['http://localhost:5432']; //port of catalog

const options: cors.CorsOptions = {
  origin: allowedOrigins,
};

app.use(cors(options));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(express.json());
app.use('/api/rating', ratingRouter);


app.get('/healthz', (req: Request, res: Response) => {
  res.status(200).json({ status: 'ok' });
});

const port = process.env.port || 3000;
const server: Server = http.createServer(app);

server.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
