package Services;

public class InputFiles {
    private String bookFile;
    private String movieFile;
    private String loanBookFile;
    private String loanMovieFile;
    private String clientFile;
    private String employeeFile;
    private String libraryFile;

    public InputFiles(String bookFile, String movieFile, String loanMovieFile, String loanBookFile, String clientFile, String employeeFile, String libraryFile) {
        this.bookFile = bookFile;
        this.movieFile = movieFile;
        this.loanMovieFile = loanMovieFile;
        this.loanBookFile = loanBookFile;
        this.clientFile = clientFile;
        this.employeeFile = employeeFile;
        this.libraryFile = libraryFile;
    }

    public String getLoanMovieFile() {
        return loanMovieFile;
    }

    public void setLoanMovieFile(String loanMovieFile) {
        this.loanMovieFile = loanMovieFile;
    }

    public String getLoanBookFile() {
        return loanBookFile;
    }

    public void setLoanBookFile(String loanBookFile) {
        this.loanBookFile = loanBookFile;
    }

    public InputFiles() {
    }

    public String getBookFile() {
        return bookFile;
    }

    public void setBookFile(String bookFile) {
        this.bookFile = bookFile;
    }

    public String getMovieFile() {
        return movieFile;
    }

    public void setMovieFile(String movieFile) {
        this.movieFile = movieFile;
    }

    public String getLibraryFile() {
        return libraryFile;
    }

    public void setLibraryFile(String libraryFile) {
        this.libraryFile = libraryFile;
    }


    public String getClientFile() {
        return clientFile;
    }

    public void setClientFile(String clientFile) {
        this.clientFile = clientFile;
    }

    public String getEmployeeFile() {
        return employeeFile;
    }

    public void setEmployeeFile(String employeeFile) {
        this.employeeFile = employeeFile;
    }



}
