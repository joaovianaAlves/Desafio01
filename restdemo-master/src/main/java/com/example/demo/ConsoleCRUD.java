package com.example.demo;

import com.example.demo.objects.Livro;
import com.example.demo.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleCRUD implements CommandLineRunner {

    @Autowired
    private FirebaseService firebaseService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Escolha uma operação:");
            System.out.println("1. Criar Livro");
            System.out.println("2. Ler Detalhes do Livro");
            System.out.println("3. Atualizar Detalhes do Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("5. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consuming the newline character after the int

            switch (choice) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    String tituloCriar = scanner.nextLine();
                    System.out.println("Digite o autor do livro:");
                    String autor = scanner.nextLine();
                    System.out.println("Digite a editora do livro:");
                    String editora = scanner.nextLine();
                    System.out.println("Digite o ano de edição do livro:");
                    String anoEdicao = scanner.nextLine();
                    System.out.println("Digite a localização do livro:");
                    String localizacao = scanner.nextLine();
                    Livro livroCriar = new Livro();
                    livroCriar.setTitulo(tituloCriar);
                    livroCriar.setAutor(autor);
                    livroCriar.setEditora(editora);
                    livroCriar.setAnoEdicao(anoEdicao);
                    livroCriar.setLocalizacao(localizacao);
                    firebaseService.saveBookDetails(livroCriar);
                    break;
                case 2:
                    System.out.println("Listando todos os livros:");
                    List<Livro> todosLivros = firebaseService.getAllBooks();
                    for (Livro livro : todosLivros) {
                        System.out.println(livro);
                    }
                    break;
                case 3:
                    System.out.println("Digite o título do livro a ser atualizado:");
                    String tituloAtualizar = scanner.nextLine();
                    Livro livroAtualizar = firebaseService.getBookDetails(tituloAtualizar);
                    if (livroAtualizar != null) {
                        System.out.println("Digite o novo título do livro:");
                        String novoTitulo = scanner.nextLine();
                        System.out.println("Digite o novo autor do livro:");
                        String novoAutor = scanner.nextLine();
                        System.out.println("Digite o novo ano do livro:");
                        String novoAnoString = scanner.nextLine();
                        int novoAno;
                        try {
                            novoAno = Integer.parseInt(novoAnoString);
                            livroAtualizar.setTitulo(novoTitulo);
                            livroAtualizar.setAutor(novoAutor);
                            livroAtualizar.setAnoEdicao(Integer.toString(novoAno)); // Convertendo int para String
                            firebaseService.updateBookDetails(livroAtualizar);
                            System.out.println("Livro atualizado com sucesso.");
                        } catch (NumberFormatException e) {
                            System.out.println("Ano inválido. Digite um número válido para o ano.");
                        }
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o título do livro a ser deletado:");
                    String tituloDeletar = scanner.nextLine();
                    firebaseService.deleteBook(tituloDeletar);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Escolha inválida.");
            }
        }

        System.out.println("Programa encerrado.");
    }
}
