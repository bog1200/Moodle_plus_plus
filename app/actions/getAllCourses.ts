"use server";

// Import necessary modules
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

export async function getAllCourses() {
    // Fetch courses from the database
    const courses = await prisma.subject.findMany({
        include: {
            teacher: {
                include: {
                    user: true,
                },
            },
        },
    });
//todo filter courses by student enrollment

    // Map the results to the desired format
    const formattedCourses = courses.map(course => ({
        name: course.name!,
        professor: course.teacher.user.name!,
        subject: course.id!,
    }));
    console.log("formatted courses: " + formattedCourses);

    return formattedCourses;
}