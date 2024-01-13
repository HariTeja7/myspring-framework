package com.myspring.helper;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class FileScanner {

	private static FileScanner fileScanner;

	public static FileScanner get() {
		if (fileScanner == null) {
			fileScanner = new FileScanner();
		}
		return fileScanner;
	}

	private ReflectionUtils reflectionUtils = null;

	public FileScanner() {
		reflectionUtils = ReflectionUtils.get();
	}

	public void scanAndDo(Class<?> initializerClazz, Consumer<Class<?>> conusmer) {
		try {
			Path currentDirectoryPath = FileSystems.getDefault().getPath("");
			String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
			Package basepackage = initializerClazz.getPackage();
			String basePackage = currentDirectoryName + "\\src\\" + basepackage.getName().replace(".", "\\");
			Path basePath = Path.of(basePackage);
			findClassAndDo(basePath, conusmer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void findClassAndDo(Path basePath, Consumer<Class<?>> conusmer) throws IOException {
		try (DirectoryStream<Path> dir = Files.newDirectoryStream(basePath)) {
			for (Path path : dir) {
				if (path.toFile().isDirectory()) {
					findClassAndDo(path, conusmer);
				} else {
					Class<?> clazz = reflectionUtils.getClassFromPackage(path.toFile());
					conusmer.accept(clazz);
				}
			}
		}
	}

}
