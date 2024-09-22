    package service;

    public interface IService {

        void createProduct();
        void updateProduct();
        void deleteProduct();
        void findProductbyname();
        void findProductById();
        void printAllProducts();
        
        void readFile(String fileName);
        void writeFile(String fileName);

        void loadBrandData(String brandFileName);
        void loadCategoryData(String categoryFileName);

    }
