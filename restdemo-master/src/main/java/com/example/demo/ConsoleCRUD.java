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

            int choice;
            while (true) {
                String input = scanner.nextLine(); // Read the input as a string
                try {
                    choice = Integer.parseInt(input); // Attempt to parse the input as an integer
                    break; // Exit the loop if an integer is successfully parsed
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Por favor, insira um número.");
                }
            }

            switch (choice) {
                case 1:
                    //Titulo
                    System.out.println("Digite o título do livro:");
                    String tituloCriar = scanner.nextLine();
                    while(tituloCriar.isEmpty()){
                        System.out.println("Título é um campo obrigatório. Por favor, insira um título válido.");
                        tituloCriar = scanner.nextLine();
                    }

                    //Autor
                    System.out.println("Digite o autor do livro:");
                    String autor = scanner.nextLine();
                    while(autor.isEmpty()){
                        System.out.println("Autor é um campo obrigatório. Por favor, insira um Autor válido.");
                        autor = scanner.nextLine();
                    }

                    //Editora
                    System.out.println("Digite a editora do livro:");
                    String editora = scanner.nextLine();
                    while(editora.isEmpty()){
                        System.out.println("Editora é um campo obrigatório. Por favor, insira uma Editora válido.");
                        editora = scanner.nextLine();
                    }

                    // AnoEdicao
                    System.out.println("Digite o ano de edição do livro:");
                    Integer anoEdicao = null;
                    while (anoEdicao == null || anoEdicao <= 0) {
                        String input = scanner.nextLine();
                        try {
                            anoEdicao = Integer.parseInt(input);
                            if (anoEdicao <= 0) {
                                System.out.println("Ano de edição deve ser um número positivo. Por favor, insira um valor válido.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ano de edição inválido. Por favor, insira um valor numérico válido.");
                        }
                    }


                    //Localizacao
                    System.out.println("Digite a localização do livro:");
                    String localizacao = scanner.nextLine();
                    while(localizacao.isEmpty()){
                        System.out.println("Localizacao é um campo obrigatório. Por favor, insira uma Localizacao válido.");
                        localizacao = scanner.nextLine();
                    }

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
                        System.out.println("Título: " + livro.getTitulo());
                        System.out.println("Autor: " + livro.getAutor());
                        System.out.println("Editora: " + livro.getEditora());
                        System.out.println("Ano de Edição: " + livro.getAnoEdicao());
                        System.out.println("Localização: " + livro.getLocalizacao());
                        System.out.println("---------------------------------------------");
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
                        Integer novoAnoString = scanner.nextInt();
                        try {
                            livroAtualizar.setTitulo(novoTitulo);
                            livroAtualizar.setAutor(novoAutor);
                            livroAtualizar.setAnoEdicao(novoAnoString); // Convertendo int para String
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
