import { Request, Response } from 'express';
import {getNotes, getProductNotes, getUserNotes, giveNote} from "@service/rating";

export const giveNoteAction = async (req: Request, res: Response) => {
    try {
        const { prodid } = req.params;
        const { note, userId } = req.body;

        await giveNote(prodid, note, userId);

        return res.status(200).json({ message: 'Note added' });
    } catch (error) {
        console.error(error);
        return res.status(500).json({ error: 'Error' });
    }
};
export const getNoteAction = async (req: Request, res: Response) => {
    try {


        const result = await getNotes();

        return res.status(200).json(result);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ error: 'Error' });
    }
};

export const getUserNotesAction = async (req: Request, res: Response) => {
    try {
        const { userId } = req.params;

        const userNotes = await getUserNotes(userId);

        return res.status(200).json(userNotes);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ error: 'Internal Server Error' });
    }
};

export const getProductNotesAction = async (req: Request, res: Response) => {
    try {
        const { prodid } = req.params;
        const productNotes = await getProductNotes(prodid);
        return res.status(200).json(productNotes);
    } catch (error) {
        console.error(error);
        return res.status(500).json({ error: 'Internal Server Error' });
    }
};