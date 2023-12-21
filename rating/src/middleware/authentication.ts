import { Response, Request, NextFunction } from 'express';
import jwt, { JwtPayload } from 'jsonwebtoken';
import { JWT_SECRET } from '@config/env';

export const authenticate = async (
  req: Request,
  res: Response,
  next: NextFunction,
) => {
  try {
    const token = req.header('Authorization')?.replace('Bearer ', '');
    if (!token) {
      throw new Error('Authentication required');
    }
    const decoded = jwt.verify(token, JWT_SECRET) as JwtPayload;
    req.body.userId = decoded.id;
    next();
    return res.status(200);
  } catch (err) {
    return res.status(401).send(err.message);
  }
};
