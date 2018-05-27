using System;

namespace Application
{
class client
{
	static void Main()
	{

		WSEmetteurService service = new WSEmetteurService();

		Console.WriteLine(service.emettre("tu vas bien ?"));


	}
}
}
