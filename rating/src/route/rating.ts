import express from 'express';

import {authenticate} from "@middleware/authentication";


import {getNoteAction,getProductNotesAction, getUserNotesAction, giveNoteAction} from "@controller/rating";

const router = express.Router();


router.use(authenticate);


router.post('/notes/product=:prodid', giveNoteAction);
router.get('/notes/user=:userId', getUserNotesAction);
router.get('/notes/product=:prodid', getProductNotesAction);
router.get('/notes/', getNoteAction);

export default router;