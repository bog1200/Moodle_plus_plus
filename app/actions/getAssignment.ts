"use server";

// Import necessary modules
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

export async function getAssignmentById(assignment_id: string) {
    // Fetch course details from the database
    return prisma.assignment.findUnique({
        where: {
            id: assignment_id,
        },
        include: {
            submissions: {
                include:
                    {files: true}
            },
            grade: true
        },
    });


}