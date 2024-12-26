"use server";
import { PrismaClient } from '@prisma/client'
import {auth} from "@/auth";
import {FileSummary} from "@/app/actions/getUploadKey";

export async function storeUpload(file: FileSummary) {
    const prisma = new PrismaClient()
    const session = await auth()
    if (!session) {
        throw new Error('Not authenticated')
    }
    const user = session.user.id!

   const account = await prisma.account.findFirst({
        where: {
            providerAccountId: user
        }
    });

    const fileUpload = await prisma.file.create({
        data: {
            fileName: file.name,
            fileType: file.type,
            fileLink: file.link!,
            owner: {
                connect: {
                    id: account!.userId
                }
            }
        }
    });
    await prisma.$disconnect();
    return fileUpload;
}