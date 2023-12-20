import {RatingModel} from "@model/rating-schema";

export const giveNote = async (productId: string, note: number, userId: string) => {
    try {
        const rating = {
            productId,
            userId,
            note,
        };
        await RatingModel.create(rating)
    } catch (error) {
        throw error;
    }
};

export const getUserNotes = async (userId: string) => {
    try {
        const userNotes = await RatingModel.find({ userId });
        return userNotes;
    } catch (error) {
        throw error;
    }
};

export const getProductNotes = async (productId: string) => {
    try {
        const productNotes = await RatingModel.find({ productId });
        return productNotes;
    } catch (error) {
        throw error;
    }
};
export const getNotes = async () => {
    try {
        const res = await RatingModel.find();
        return res;
    } catch (error) {
        throw error;
    }
};
