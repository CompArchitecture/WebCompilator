# ვებ კომპილატორი
### ფუნქცია
მოცემული პროექტი არის ვებ აპლიკაცია, რომლის მეშევობიტაშ შესაძლებელია საიტზე კოდის ატვირთვა და მოცემული ამოცანისთვის ტესტირება და რეზულტატის დაბრუნება. მომხმარებელს შეუძლია აირჩიოს კომპილატორი (ჩვენ შემთხვევაში გვაქვს cpp-ის GNU კომპილატორი) [თუმცა მუშაობის პრინციპიდან გამომდინარე საკმაოდ მარტივია სხვა კომპილატორების ინტეგრაციაც] ატვირთოს კოდი, შემდეგ პროგრამა დააკომპილირებს, გაუშვებს ტესტებზე და დაუბრუნებს რეზულტატს [კომპილაციის შეცდომა | runtime ერორი | მეხსიერების ლიმიტი | დროის ლიმიტი | არასწორი პასუხი | Accept ]

### ნაწილები
ჩვენი აპლიკაცია შედგება ორი მოდულისგან, პირობითად ერთი Front-end, მოდული რომელიც პასუხისმგებელია მომხმარებლისთვის მორგებული ინტერფეისის გამზადება, კოდის ასატვირთად და პასუხების გამოსაჩენად და server-side ნაწილი რომელიც აკომპილირებს და უშვებს კოდს და აბრუნებს პასუხს.
გამოცანის გამოწვევბიდან გამომდინარე ჩვენ აღწერაში ვკონცენტრირდებით server-side ნაწილზე.

## იმპლემენტაცია
პროგრმის ძირითადი ოპერაცა არის სხვა გარე პროგრამის დასტარტვა როგორც პროცესად, პირველ რიგში ხდება gcc-ის დასტარტვა რომელსაც გადაეცემა დასაკომპილირებელი ფიალის სახელი, თუ პროცესის დასრულებისას სტატუსი დაბრუნდება 0 ნიშნავს რომ კომპილაციამ სწორად იმუსავა და პროცესი გრძელდება, ხოლო თუ დაბრუნდა 0-ისაგან განსხვავებული რიცხვი, მოცმეულ შემთხვევაში ხდება გაჩერება და კომპილაციის შეცდომის დაბრუნება.
შემდეგ ეტაპზე ხდება არსებული დაბრუნებული პროგრამის გაშვება (სიმარტივისთVის gcc ის გადავეცით -o პარამეტრი და დავრქვით ჩვენთვის საურველი სახელი, შეედეგად უკვე ვიცით რომ არსებული მიღებული პროგრამის სახელი და შეგვიძლია გაშვება. 
პროგრამის გაშვების პარალელურად ხდება background threadის დასტარტვა, რომელიც ასევე უშვებს პროგრამას რომელიც პროგრამის მუშაობის დასრულებამდე იღებს ინფორმაციას მისი გამოყენებული ემხსიერებისა და დროის(cpu-ს მოხმარების) შესახებ. rogoრცკი რესურსი გადააჭარბებს ლიმიტს მოცემული thread მოკლავს გაშვებულ პროცესს.
შესაბამისად გვაქვს კლასი რომელშიც ეთითება მიმდინარე შეცდომა და ხდება შეცდომის მესიჯთან map-ირება და დაბრუნება. გაშვებული პროგრამის დასაწყისში ვიღებთ input stream-ს და ვწერთ შიგნით არსებულ input-ს და პროცესის დაბრუნებისას ვIღებთ oupput stream-ს და ვკიტხულობთ და ვადარებთ რეზულტატს. როგორც მოთხოვნაში იყო კითხვა ხდება stdin ით ანუ კონსოლიდან და ანალოგიურად წაკითხვაც. არსებულ მოდულს გადააეცემა თითოული ტესტსტების დირექტორია(მისამართი), ტესტის ფაილი (მისამართი) და მეხსიერების და დროის შეზღუდვები, რომელიც არსებულ პროცესს (რომელიც აღვწერეთ) სათიტაო ტესტისთვის უშვებს, რაც მერამდენე ტესტზე მოხდა შეცდომა, ამის დადგენის საშუალებას იძლევა.
### სპეციფიკაცია
პროექტის server side ნაწილი დაწერილია java-ზე და დაფუძნებულია რამდნეიმე გარე პროგრამაზე, მაგ როგორიცაა g++ (უნდა იყოს დაყენებელი არსებული კომპილატორი) და ps (პროცესის ინფორმაციის გასაგებად, არსებული პროგრამა ჩაშენებული აქვს linux-ს, სხვა ოპერაციულ სისტემაზე თუ პრობლემა შეიქმნა გაუშვით ლინუქსზე ან ლინუქსის კონტეინერში)
Front-ისთვის:
პროგრამა უნდა დაიბილდოს და გაეშვას java8-ზე.. 
webserver-ად გამოიყენეთ tomcat 9.


#პროექტზე მუშაობდნენ
ბერიძე ნიკა

ლაბარტყავა გური

სახელაშვილი მამუკა

ქასრაშვილი ანდრო 

