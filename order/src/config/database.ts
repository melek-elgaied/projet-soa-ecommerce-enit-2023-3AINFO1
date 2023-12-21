import { pino } from 'pino';
import { connect } from 'mongoose';

import { MONGODB_PASSWORD, MONGODB_URI, MONGODB_USERNAME } from '@config/env';

const logger = pino();
async function connectDb() {
  try {
    logger.info('Connecting to mongodb');
    await connect(MONGODB_URI, {
      auth: {
        username: MONGODB_USERNAME,
        password: MONGODB_PASSWORD,
      },
      authMechanism: 'SCRAM-SHA-1',
    });
    logger.info('Connected to mongodb');
  } catch (error) {
    logger.error(`Error while connecting to mongodb ${JSON.stringify(error)}`);
  }
}

export default connectDb;
