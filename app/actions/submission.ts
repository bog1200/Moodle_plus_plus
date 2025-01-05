"use server";

// Import necessary modules
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

export async function deleteSubmission(submission_id: string) {
    // Fetch course details from the database

    return prisma.assignmentSubmission.delete({
        where: {
            id: submission_id,
        },
    });


}