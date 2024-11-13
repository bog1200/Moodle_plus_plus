/*
  Warnings:

  - You are about to drop the column `accountId` on the `File` table. All the data in the column will be lost.

*/
-- DropForeignKey
ALTER TABLE `File` DROP FOREIGN KEY `File_accountId_fkey`;

-- AlterTable
ALTER TABLE `File` DROP COLUMN `accountId`;
