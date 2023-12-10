package main.homework5;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    private static final String BACKUP_DIRECTORY = "C:\\Users\\user\\IdeaProjects\\backup directory";

    public static void main(String[] args) throws IOException {
        /*
        Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
         */

        String sourceDirectory = ".";
        backupFiles(sourceDirectory, BACKUP_DIRECTORY);
    }

    /**
     * Метод для создания бэкапа
     * @param srcDirectory Строковое представление пути для которого создается бэкап
     * @param destinationDirectory Строковое представление пути для создания бэкапа
     * @throws IOException При возникновении ошибок при работе с файлами
     */
    public static void backupFiles(String srcDirectory, String destinationDirectory) throws IOException {

        Path src = Paths.get(srcDirectory);
        Path dest = Paths.get(destinationDirectory);

        // если такой директории не существует создаем ее
        if (!Files.exists(dest)) {
            Files.createDirectory(dest);
        }

        // Получаем список объектов Path из начальной директории
        Stream<Path> list = Files.list(src);
        list.forEach(path -> {
            // Если мы попали в директорию, то рекурсивно вызываем для нее метод бэкапа
            if (Files.isDirectory(path)) {
                try {
                    backupFiles(srcDirectory + "\\" + path.getFileName(), destinationDirectory + "\\" + path.getFileName());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                // Если же мы попали на файл, то вызываем метод копирования файла
            } else if (Files.isRegularFile(path)) {
                try {
                    copyFile(srcDirectory + "\\" + path.getFileName(), destinationDirectory + "\\" + path.getFileName());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

    }

    /**
     * Метод копирования файла
     * @param source Строковое представление пути до файла, который копируем
     * @param destination Строковое представление пути для файла куда собираемся копировать
     * @throws IOException При возникновении ошибок при работе с файлами
     */
    public static void copyFile(String source, String destination) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(destination);
             FileInputStream fis = new FileInputStream(source)) {

            byte[] buffer = new byte[1024];
            int length;
            while((length = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
            fos.flush();
        }
    }
}
