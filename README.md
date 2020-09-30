Version 9.17.0

## ENG ( RUS version below )


## How to use:

1. Add links to our maven-repository:
    
       maven {url "https://maven.caramelads.com/repository/maven-releases"}
       maven {url "https://chartboostmobile.bintray.com/Chartboost"}
       

2. Add dependency in build.gradle:

          implementation ('com.caramelads:sdk:9.17.0')
    
3. Scenarios of ads showing:
	
	Attention: 
	
	If you use our sdk before new time-request rules pls remove old integration code and our old proguard-rules.pro (except ad showing places) 

	Ad loading:	
	In the class extends Application class using Application.AcitivityLifecycleCallbacks each 150-240sec (showed in example) If you 	want the easiest way then just copy package 'caramel' from test integration  (it has two classes: CaramelIntegration and 		CaramelApp) to your project and add CaramelApp in manifest.xml, add your additional code, if necessary.
			
			<application
        			android:name=".caramel.CaramelApp"
      
	Ads showing:
	
	Call method CaramelIntegration.showAds() to show ad
	
	Elements in the application, that will show ad by click, excepting leaving from application with ‘back’ buttons. In the games 		tipical showing example when player moves to next level or game over. In the other apps tipical showing example – when move to a 	 new screen(through activity or fragment) and back, also you can use some user actions if it exists (such as file saving, ending 	 of text editing and etc). 
	
	You can download test integration example, which implements above described algorithm.


# Thank you for use our product!


# RUS 




## Как использовать:

1.	Добавьте ссылку на наш maven-репозиторий:

       		maven {url "https://maven.caramelads.com/repository/maven-releases"}
       		maven {url "https://chartboostmobile.bintray.com/Chartboost"}

2.	Добавьте зависимость в build.gradle:

                implementation ('com.caramelads:sdk:9.17.0')

3.	Сценарии показа рекламы:

	Внимание:
	
	Если вы использовали наше sdk перед новыми правилами тайминга запросов рекламы, пожалуйста удалите старый код интеграции и старые proguard-rules.pro		(исключая места показа рекламы)
	
	Загрузка рекламы:
	В классе, наследующем класс Application, используя Application.ActivityLifecycleCallbacks каждые 150-240 секунд (показано в 		примере). Если вы ищете наиболее легий путь просто скопируйте пакет из тестовой интеграции 'caramel' (В нем находятся классы 		CaramelIntegration и CaramelApp) добавьте в manifest.xml, при необходимости можно добавлять свой код
	
		<application
        		android:name=".caramel.CaramelApp"
	    
	Отображение рекламы:
	
	Метод CaramelIntegration.showAds() отображает рекламу
	
	Элементы в приложении, при клике на которые, осуществляется показ рекламы, за исключением – кнопок back при выходе из 	 		приложения. В играх типичный пример показа при переходе на новый уровень. В обычных приложениях типичный пример показа при 		переходе на новый экран(через активность или фрагмент) и возврате, также можно использовать действия пользователя, если такие 		функции есть в приложении(сохранение файла, окончание редактирования текста и т.п.). 
	
	Вы можете скачать пример тестовой интеграции, реализующей вышеописанный алгоритм.



# Спасибо, что используете наш продукт!

