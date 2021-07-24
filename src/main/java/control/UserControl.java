package control;

import dao.CarDao;
import enty.Car;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserControl {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    CarDao carDao = new CarDao();

    @SneakyThrows
    public void cmdList() {

        System.out.println("Нажмите 1, чтобы создать авто");
        System.out.println("Нажмите 2, чтобы редактировать авто");
        System.out.println("Нажмите 3, чтобы найти авто по id");
        System.out.println("Нажмите 4, чтобы найти авто по названию");
        System.out.println("Нажмите 5, чтобы найти авто в ценовом диапозоне");
        System.out.println("Нажмите 6, чтобы показать все авто");
        System.out.println("Нажмите 7, чтобы удалить авто");
        System.out.println("Нажмите 8, чтобы удалить все авто");
        switch (read.readLine().replaceAll(" ", "")) {
            case ("1"):
                createAuto();
                break;
            case ("2"):
                redactAuto();
                break;
            case ("3"):
                findById();
                break;
            case ("4"):
                findByName();
                break;
            case ("5"):
                findPriceRange();
                break;
            case ("6"):
                showAll();
                break;
            case ("7"):
                delete();
                break;
            case ("8"):
                deleteAll();
                break;
            default:
                incorrect();

        }

    }

    @SneakyThrows
    public void createAuto() {
        Car car = new Car();
        System.out.println("Введите название авто");
        car.setTitle(read.readLine());
        System.out.println("Введите цену авто");
        car.setPrice(Integer.parseInt(read.readLine()));
        System.out.println("Введите дату производства в формате \"2015-02-20\"");
        car.setDateManufacture(LocalDate.parse(read.readLine()));
        System.out.println("Введите дату продажи в формате \"2015-02-20\"");
        car.setSellDate(LocalDate.parse(read.readLine()));
        System.out.println("Введите тип двигателя");
        car.setGearType(read.readLine());
        System.out.println("Введите объем топлива");
        car.setFuelVolume(Integer.parseInt(read.readLine()));
        carDao.createCar(car);
        cmdList();
    }

    @SneakyThrows
    public void redactAuto() {
        System.out.println("Введите id авто для изменения");
        int id = Integer.parseInt(read.readLine());;
        if (!carDao.checkExistById(id)){
            System.out.println("Авто не найдено");
            redactAuto();
        }
        System.out.println("Введите новое название");
        String newTitle = read.readLine();
        System.out.println("Введите новую цену");
        int newPrice = Integer.parseInt(read.readLine());
        System.out.println("Введите новый тип двигателя");
        String newGearType = read.readLine();
        System.out.println("Введите новый объем топлива");
        int newFuelVolume = Integer.parseInt(read.readLine());
        carDao.redactCar(id, newTitle, newPrice, newGearType, newFuelVolume);
        cmdList();
    }

    @SneakyThrows
    public void findById() {
        System.out.println("Введите id для поиска");
        int id = (Integer.parseInt(read.readLine()));
        if (!carDao.checkExistById(id)){
            System.out.println("Авто не найдено");
            findById();
        }
        System.out.println(carDao.getCarById(id));
        cmdList();
    }

    @SneakyThrows
    public void findByName() {
        System.out.println("Ведите название для поиска");
        String title = read.readLine();
        if (!carDao.checkExistByName(title)){
            System.out.println("Авто не найдено");
            findByName();
        }
        System.out.println(carDao.getCarByName(title));
        cmdList();
    }

    @SneakyThrows
    public void findPriceRange() {
        System.out.println("Введите нижний порог цены");
        int start = Integer.parseInt(read.readLine());
        System.out.println("Введите верхний порог цены");
        int finish = Integer.parseInt(read.readLine());
        for (Car c : carDao.getCarsByRange(start,finish)){
            System.out.println(c);
        }
        cmdList();
    }

    public void showAll() {
        for (Car c : carDao.getCars()) {
            System.out.println(c);
        }
        cmdList();
    }

    @SneakyThrows
    public void delete() {
        System.out.println("Введите id для удаления");
        int id = Integer.parseInt(read.readLine());
        if (!carDao.checkExistById(id)){
            System.out.println("Авто не найдено");
            delete();
        }
        carDao.deleteCar(id);
        cmdList();
    }

    public void deleteAll() {
        carDao.deleteCars();
        cmdList();
    }

    public void incorrect() {
        System.out.println("Неподдерживаемая команда");
        cmdList();
    }
}
